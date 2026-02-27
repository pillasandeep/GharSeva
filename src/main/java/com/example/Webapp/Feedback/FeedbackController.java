package com.example.Webapp.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import com.example.Webapp.Entity.FeedbackEntity;
import com.example.Webapp.Repository.FeedbackRepository;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/feedback")
    public String loadFeedbackPage(Model model) {
        model.addAttribute("feedback", new FeedbackEntity());
        return "feedback";
    }

    @PostMapping("/submit")
    public String sentFeedBack(@ModelAttribute("feedback") FeedbackEntity feedback) {

        // SAVE INTO DATABASE
        feedbackRepository.save(feedback);

        return "home"; // or "home"
    }
}