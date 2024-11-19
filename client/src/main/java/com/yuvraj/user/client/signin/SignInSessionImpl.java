package com.yuvraj.user.client.signin;

import com.yuvraj.user.client.remote.SignInClient;
import com.yuvraj.user.common.AuthContext;
import com.yuvraj.user.common.Contact;
import com.yuvraj.user.common.VerifyRequest;
import com.yuvraj.user.common.VerifyResponse;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class SignInSessionImpl implements SignInSession {
    private final SignInClient client;
    private final Contact contact;
    private String sessionId;
    private VerifyResponse verifyResponse;

    public SignInSessionImpl(AuthContext context, Contact contact) {
        this.client = new SignInClient(context.userUrl());
        this.contact = contact;
    }

    @Override
    public CompletableFuture<Void> sendOTP() {
        return client.sendOTP(contact).thenAccept(sessionResponse -> {
            this.sessionId = sessionResponse.getSessionId();
        });
    }

    @Override
    public CompletableFuture<Void> verifyOTP(int otp) {
        VerifyRequest verifyRequest = new VerifyRequest(sessionId, otp);
        return client.verifyOTP(verifyRequest).thenAccept(verifyResponse -> {
            this.verifyResponse = verifyResponse;
        });
    }

    @Override
    public VerifyResponse getVerifyResponse() {
        return verifyResponse;
    }
}
