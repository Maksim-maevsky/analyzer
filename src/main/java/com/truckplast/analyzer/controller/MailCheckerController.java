package com.truckplast.analyzer.controller;

import com.truckplast.analyzer.facade.MailCheckerFacade;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@Data
@RequiredArgsConstructor
public class MailCheckerController {

    private final MailCheckerFacade mailCheckerFacade;

    @Operation(summary = "Check mail")
    @GetMapping("/check")
    public void checkNewMessages() {

        mailCheckerFacade.checkEmailAndSaveInfo();

    }
}
