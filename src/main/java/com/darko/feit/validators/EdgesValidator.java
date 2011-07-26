package com.darko.feit.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.darko.feit.form.Edge;

@Component
public class EdgesValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Edge.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "cost.required");
		Edge edge = (Edge) object;
		if(edge.getCost()!=null && edge.getCost()<10){
			errors.rejectValue("cost","cost.short");
		}
		if(edge.getFromPlace().getId()==-1){
			errors.reject("name.required");
		}
		if(edge.getToPlace().getId()==-1){
			errors.reject("name.required");
		}
		if(edge.getFromPlace().getId()==edge.getToPlace().getId()){
			errors.reject("edge.sameplace");
		}
		
	}

}
