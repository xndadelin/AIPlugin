package org.aIPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AICommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Hey! You need to actually write something after /ask!");
            return true;
        }

        HttpClient client = HttpClient.newHttpClient();

        String prompt = String.join(" ", args);
        prompt = "This is a Minecraft related question. Please try to answer as shortly and concise as possible. Only text, no markdown! " + prompt;

        String body = """
                {
                    "messages": [{"role": "user", "content": "%s"}],
                    "model": "meta-llama/llama-4-maverick-17b-128e-instruct"
                }
                """.formatted(prompt);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://ai.hackclub.com/chat/completions"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
                JsonObject message = json.getAsJsonArray("choices")
                        .get(0).getAsJsonObject()
                        .getAsJsonObject("message");
                String content = message.get("content").getAsString();

                sender.sendMessage(ChatColor.GREEN + content);
            } else {
                sender.sendMessage(ChatColor.RED + "AI API error code: " + response.statusCode());
            }

        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "An error occurred: " + e.getMessage());
        }

        return true;
    }
}
