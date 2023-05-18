package com.zerobase.user.application;

import com.zerobase.user.client.MailgunClient;
import com.zerobase.user.client.mailgun.SendMailForm;
import com.zerobase.user.domain.SignUpForm;
import com.zerobase.user.exception.ErrorCode;
import com.zerobase.user.exception.MemberException;
import com.zerobase.user.domain.entity.Member;
import com.zerobase.user.service.SignUpMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpApplication {
    private final MailgunClient mailgunClient;
    private final SignUpMemberService signUpMemberService;

    public void memberVerify (String email, String code) {
        signUpMemberService.verifyEmail(email, code);
    }

    public String memberSignUp(SignUpForm form) {
        if(signUpMemberService.isEmailExist(form.getEmail())) {
            throw new MemberException(ErrorCode.ALREADY_REGISTER_USER);
            //exception
        } else {
            Member m = signUpMemberService.signUp(form);

            String code = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("zerobase-test@email.com")
                    .to(form.getEmail())
                    .subject("Verification Email!")
                    .text(getVerificationEmailBody(m.getEmail(), m.getUsername(), "member", code))
                    .build();
            log.info("Send email result : " + mailgunClient.sendEmail(sendMailForm).body());

            signUpMemberService.changeMemberValidateEmail(m.getId(), code);
            return "회원 가입에 성공하였습니다.";
        }
    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String type, String code) {
        StringBuilder builder = new StringBuilder();
        return builder.append("Hello").append(name).append("! Please Click Link for verification.\n\n")
                .append("http://localhost:8080/signup/"+type+"/verify/?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();
    }
}
