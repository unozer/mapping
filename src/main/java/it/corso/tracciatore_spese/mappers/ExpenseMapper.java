package it.corso.tracciatore_spese.mappers;

import it.corso.tracciatore_spese.models.Expense;
import it.corso.tracciatore_spese.models.ExpenseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    @Mapping(source = "category.id", target = "category_id")
    ExpenseDTO entityToDto(Expense expense);

    @Mapping(source = "category_id", target = "category.id")
    Expense dtoToEntity(ExpenseDTO expenseDTO);
}
