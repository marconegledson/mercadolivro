package com.mercadolivro.mercadolivro.validation

import com.mercadolivro.mercadolivro.service.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvaliableValidator(var customerService: CustomerService): ConstraintValidator<EmailAvaliable, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrBlank()){
            return false
        }
        return customerService.emailAvaliable(value)
    }

}
