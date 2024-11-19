package com.yuvraj.user.client.signup;

import com.yuvraj.user.client.remote.SignUpClient;
import com.yuvraj.user.common.AuthContext;
import com.yuvraj.user.common.Contact;
import com.yuvraj.user.common.user.User;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
public class SignUpSessionImpl implements SignUpSession {
    private final SignUpClient client;
    private final UserCreator userCreator;
    private final Contact contact;
    private String sessionId;

    public SignUpSessionImpl(AuthContext context, Contact contact) {
        this.client = new SignUpClient(context.userUrl());
        this.userCreator = new UserCreator(client, context);
        this.contact = contact;
    }

    @Override
    public CompletableFuture<Void> sendOTP() {
        if (contact.getEmailId() != null) {
            return client.sendOTP(contact.getEmailId()).thenAccept(id -> {
                sessionId = id;
            });
        } else {
            return client.sendOTP(contact.getPhoneNumber()).thenAccept(id -> {
                sessionId = id;
            });
        }
    }

    @Override
    public CompletableFuture<User> signUp(int otp) {
        return userCreator.create(contact, sessionId, otp);
    }
}
