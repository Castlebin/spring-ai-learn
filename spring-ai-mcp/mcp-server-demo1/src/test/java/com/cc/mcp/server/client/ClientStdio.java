package com.cc.mcp.server.client;

import java.util.Map;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.ListToolsResult;

// 一个简单的 Java MCP 客户端，使用 标准输入输出（stdio）  与 MCP 服务器进行通信
public class ClientStdio {

    public static void main(String[] args) {
        // MCP 服务器的参数 （看得出来，是直接执行 jar ）
        // 打包好的 jar，就是一个 可执行的程序，也就是我们的 MCP 服务器
        // MCP Client 调用 MCP Server
        var stdioParams = ServerParameters.builder("java")
                .args("-jar",
                        "D:\\Study\\study-code\\spring-ai-learn\\spring-ai-mcp\\mcp-server-demo1\\target\\mcp-server"
                                + "-demo1-0.0.1-SNAPSHOT.jar")
                .build();

        var transport = new StdioClientTransport(stdioParams);
        var client = McpClient.sync(transport).build();

        client.initialize();

        // List and demonstrate tools
        // 列出所有的工具
        ListToolsResult toolsList = client.listTools();
        System.out.println("Available Tools = " + toolsList);

        // 调用 tools
        CallToolResult weatherForcastResult = client.callTool(
                new CallToolRequest("getWeatherForecastByLocation",
                        Map.of("latitude", "47.6062", "longitude", "-122.3321"))
        );
        System.out.println("Weather Forcast: " + weatherForcastResult);

        CallToolResult alertResult = client.callTool(
                new CallToolRequest("getAlerts", Map.of("state", "NY"))
        );
        System.out.println("Alert Response = " + alertResult);

        client.closeGracefully();
    }

}
