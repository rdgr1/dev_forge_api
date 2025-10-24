package org.rod.dev.report.forge.config;

import dev.langchain4j.agent.tool.P;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rod.dev.report.forge.model.User;
import org.rod.dev.report.forge.repo.UserRepo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartupRunner implements ApplicationRunner {
    private final UserRepo repo;
    private final PasswordEncoder encoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
         final String email = "rvidaldias@gmail.com";
         if (!repo.existsByEmail(email)){
             log.info("Injetando Conta Default");
             var defaultUser = new User();
             defaultUser.setNome("Rodger Vidal Dias");
             defaultUser.setEmail(email);
             defaultUser.setSenha(encoder.encode("231426995647"));
             repo.save(defaultUser);
         } else {
             log.info("Conta Default Já Injetada!");
         }
    }
}
