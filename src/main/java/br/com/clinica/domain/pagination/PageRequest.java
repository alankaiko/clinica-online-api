package br.com.clinica.domain.pagination;

public class PageRequest {

    private final int pageNumber;
    private final int pageSize;

    public PageRequest(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number não pode ser negativo");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size deve ser maior que zero");
        }
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }
}
