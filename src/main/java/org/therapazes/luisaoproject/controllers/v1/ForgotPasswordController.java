package org.therapazes.luisaoproject.controllers.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.dto.ChangePassword;
import org.therapazes.luisaoproject.dto.VerifyEmailDto;
import org.therapazes.luisaoproject.services.ForgotPasswordService;


@RestController
@RequiredArgsConstructor
@RequestMapping("v1/forgot-password")
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @Operation(summary = "Gera um email de recuperação de senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email enviado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao enviar email"),
            @ApiResponse(responseCode = "404", description = "Email não encontrado")
    })
    @PostMapping("/verify-mail")
    public ResponseEntity<String> verifyEmail(@RequestBody VerifyEmailDto email) {
        try {
            return ResponseEntity.ok(forgotPasswordService.verifyEmail(email.getEmail()));
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Muda a senha do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar senha"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PostMapping("/change-password")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword) {
        try {
            return ResponseEntity.ok(forgotPasswordService.changePasswordHandler(changePassword));
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

