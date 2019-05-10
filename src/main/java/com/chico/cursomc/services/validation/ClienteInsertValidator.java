package com.chico.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.chico.cursomc.domain.enums.TipoCliente;
import com.chico.cursomc.dto.ClienteNewDTO;
import com.chico.cursomc.resources.exceptions.FieldMessage;
import com.chico.cursomc.services.validation.utils.DocumentUtil;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
// inclua os testes aqui, inserindo erros na lista
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !DocumentUtil.isValidCPF(objDto.getCpfOuCnpj()) ) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
		}
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !DocumentUtil.isValidCNPJ(objDto.getCpfOuCnpj()) ) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
		}		
				
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
