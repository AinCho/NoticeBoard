package com.zerobase.user.client;

import com.zerobase.user.client.mailgun.SendMailForm;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun")
public interface MailgunClient {
    @PostMapping("sandbox7dbc97f04cba4ba39dbf863fcdf2825f.mailgun.org/messages")
    Response sendEmail(@SpringQueryMap SendMailForm form);
}
