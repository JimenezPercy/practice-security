package com.example.practice.demo.dto;

import com.example.practice.demo.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public interface BasicView{}
    public interface DetailView extends BasicView{}

    @JsonView(BasicView.class)
    @MyConstraint
    private Integer id;
    @JsonView(BasicView.class)
    private String username;
    @JsonView(DetailView.class)
    @NotNull
    private String password;
    @JsonView(BasicView.class)
    private Integer age;

}
