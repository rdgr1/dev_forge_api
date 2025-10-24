package org.rod.dev.report.forge.controller;

import dev.langchain4j.service.Result;
import org.rod.dev.report.forge.dto.RelatorioRequest;
import org.rod.dev.report.forge.service.AssistantAiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gemini")
public class GoogleAssistController
{
    private final AssistantAiService assistantAiService;

    public GoogleAssistController(AssistantAiService assistantAiService) {
        this.assistantAiService = assistantAiService;
    }
    @PostMapping()
    public String gerarRelatorio(@RequestBody RelatorioRequest relatorio) {
        String prompt = "Commit: " + relatorio.commit() + "\n\nCódigo:\n" + relatorio.codigo();
        Result<String> result = assistantAiService.handleRequest(prompt);
        return result.content();
    }
}
