package com.infovision.canteen.service;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestParam;

import com.infovision.canteen.exception.FeedbackException;

public interface FeedbackService {

	String postWebsiteFeedback(UUID empId,String feedback ,double rating) throws FeedbackException;

	Object getEmpsFeedback()throws FeedbackException;

}
