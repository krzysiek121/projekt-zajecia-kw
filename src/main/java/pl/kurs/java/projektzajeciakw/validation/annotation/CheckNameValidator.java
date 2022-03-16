package pl.kurs.java.projektzajeciakw.validation.annotation;

import lombok.RequiredArgsConstructor;
import pl.kurs.java.projektzajeciakw.repository.CompanyRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@RequiredArgsConstructor
public class CheckNameValidator implements ConstraintValidator<CheckName, String> {

   private final CompanyRepository companyRepository;
   @Override
   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return companyRepository.findAll().stream().anyMatch(z ->z.getName().equals(obj));
   }
}



