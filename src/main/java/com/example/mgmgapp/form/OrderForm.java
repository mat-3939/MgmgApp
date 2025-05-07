package com.example.mgmgapp.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderForm {

    @NotBlank(message = "姓を入力してください")
    private String lastName;

    @NotBlank(message = "名を入力してください")
    private String firstName;

    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "有効なメールアドレスを入力してください")
    private String email;

    @NotBlank(message = "郵便番号を入力してください")
    private String postcode;

    @NotBlank(message = "住所を入力してください")
    private String address;

    @NotBlank(message = "電話番号を入力してください")
    private String tel;

    @NotBlank(message = "カード番号を入力してください")
    private String cardNumber;

    @NotBlank(message = "カード有効期限を入力してください")
    private String cardExpiry;

    @NotBlank(message = "セキュリティコードを入力してください")
    private String cardCvv;
    
    @NotBlank(message = "カード名義人を入力してください")
    private String cardHolderName;

} 