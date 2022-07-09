package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestParam;

import com.infovision.canteen.exception.FeedbackException;
import com.infovision.canteen.model.feedback.Feedback;

public interface FeedbackService {

	String postWebsiteFeedback(UUID empId,String feedback ,double rating) throws FeedbackException;

	List<Feedback> getWebisteFeedback()throws FeedbackException;

}
