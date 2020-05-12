package com.ruoyi.app.controller.sys.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "app_user")
public class AppUser implements Serializable
{
    //
    private static final long serialVersionUID = -4387619029018315254L;

    @Id
    Long                      Id;

    String                    username;

    String                    password;

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}