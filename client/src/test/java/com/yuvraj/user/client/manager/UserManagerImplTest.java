package com.yuvraj.user.client.manager;

import com.yuvraj.user.common.App;
import com.yuvraj.user.common.AuthConfig;
import com.yuvraj.user.common.Contact;
import com.yuvraj.user.common.signup.Device;
import com.yuvraj.user.common.signup.DeviceType;
import com.yuvraj.user.common.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerImplTest {
    private static UserManager userManager;

    private static Path path = Path.of(System.getProperty("user.home")).resolve("UserData");

    private static Logger LOGGER = Logger.getLogger(UserManagerImplTest.class.getName());

    private static Device randomDevice() {
        return new Device(
                UUID.randomUUID().toString(),
                "Yuvraj's Iphone",
                "IOS18",
                UUID.randomUUID().toString(),
                DeviceType.IOS
        );
    }

    public static void main(String[] args) {
        userManager = getUserManager();
        init();
    }

    private static void init() {
       while (true) {
           System.out.println("1. SignUp By Email");
           System.out.println("2. SignUp By Phone");
           System.out.println("3. SignIn by Email");
           System.out.println("4. SignIn by Phone");
           System.out.println("5. SignIn");
           System.out.print("> ");
           Scanner in = new Scanner(System.in);
           int choice = in.nextInt();
           switch (choice) {
               case 1:
                   signUpWithEmail();
                   break;
               case 2:
                   break;
               case 3:
                   signinWithemail();
                   break;
               case 4:
                   break;
               case 5:
                   offlineSignIn();
                   break;
               default:
                   System.out.println("Invalid choice");
                   break;

           }
       }
    }

    private static void offlineSignIn() {
        try {
            Scanner userIdScanner = new Scanner(System.in);
            System.out.print("UserID: ");
            String userId = userIdScanner.next();
            var user = userManager.signIn(userId).join();
            LOGGER.info("id:" + user.userId());
            LOGGER.info("apiKey:" + user.apiKey());
            LOGGER.info("email:" + user.email());
            LOGGER.info("phone number:" + user.phoneNumber());
            Thread.sleep(10000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void signinWithemail() {
        try {
            Scanner emailScanner = new Scanner(System.in);
            System.out.print("Enter email: ");
            String email = emailScanner.next();
            var signInSession = userManager.createSignInSession(Contact.email(email));
            signInSession.sendOTP().join();
            System.out.println("OTP Sent Successfully");
            System.out.print("Enter OTP: ");
            Scanner otpScanner = new Scanner(System.in);
            String otp = otpScanner.next();
            signInSession.verifyOTP(Integer.parseInt(otp)).join();
            var verifyResp = signInSession.getVerifyResponse();
            var user = userManager.signIn(verifyResp.getUserId()).join();
            LOGGER.info("id:" + user.userId());
            LOGGER.info("apiKey:" + user.apiKey());
            LOGGER.info("email:" + user.email());
            LOGGER.info("phone number:" + user.phoneNumber());
            Thread.sleep(10000);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void signUpWithEmail() {
        try {
            Scanner emailScanner = new Scanner(System.in);
            System.out.print("Enter email: ");
            String email = emailScanner.next();
            var signUpSession = userManager.createSignUpSession(Contact.email(email));
            signUpSession.sendOTP().join();
            System.out.println("OTP Sent Successfully");
            System.out.print("Enter OTP: ");
            Scanner otpScanner = new Scanner(System.in);
            String otp = otpScanner.next();
            User user = signUpSession.signUp(Integer.parseInt(otp)).join();
            LOGGER.info("id:" + user.userId());
            LOGGER.info("apiKey:" + user.apiKey());
            LOGGER.info("email:" + user.email());
            LOGGER.info("phone number:" + user.phoneNumber());
            Thread.sleep(10000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static UserManager getUserManager() {
        var authConfig = new AuthConfig(App.SOCIAL, randomDevice(), "");
        return new UserManagerImpl(path, authConfig);
    }
}