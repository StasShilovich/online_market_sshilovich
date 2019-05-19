//package com.gmail.shilovich.stas.jd2.springboot.controller;
////
////import com.gmail.shilovich.stas.jd2.springboot.config.SpringbootApplication;
////import org.junit.Assert;
////import org.junit.Before;
////import org.junit.Test;
////import org.junit.runner.RunWith;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.mock.web.MockServletContext;
////import org.springframework.test.context.ContextConfiguration;
////import org.springframework.test.context.junit4.SpringRunner;
////import org.springframework.test.context.web.WebAppConfiguration;
////import org.springframework.test.web.servlet.MockMvc;
////import org.springframework.test.web.servlet.setup.MockMvcBuilders;
////import org.springframework.web.context.WebApplicationContext;
////
////import javax.servlet.ServletContext;
////
////@RunWith(SpringRunner.class)
////@ContextConfiguration(classes = { SpringbootApplication.class })
////@WebAppConfiguration
////public class UserControllerIntegrationTest {
////
////    @Autowired
////    private WebApplicationContext context;
////
////    private MockMvc mvc;
////
////    @Before
////    public void setup() throws Exception {
////        this.mvc = MockMvcBuilders
////                .webAppContextSetup(this.context)
////                .build();
////    }
////
////    @Test
////    public void shouldGetSucceedWith200ForUser() throws Exception {
////        ServletContext servletContext = context.getServletContext();
////        Assert.assertNotNull(servletContext);
////        Assert.assertTrue(servletContext instanceof MockServletContext);
////        Assert.assertNotNull(context.getBean("UserController"));
////    }
////}