package com.br.service.everson.libraryapi.api.appconstants;

public class VariableConst {
    public static final String DEFAULT_NUMERO_PAGINA = "0";
    public  static final String DEFAULT_TOTAL_PAGINA = "10";
    public static final String DEFAULT_SORT_POR = "id";
    public static final String DEFAULT_SORT_DIRECAO = "asc";

//isso pra service validar sort
//    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//            : Sort.by(sortBy).descending();
//    Pageable pageable = PageRequest.of(page, size, sort);
}
