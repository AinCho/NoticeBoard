package com.zerobase.noticeboard.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paging {
    private int pageSize;
    private int totalPage;
    private int pageNum;
    private int pageNavSize;
    private long totalRecode;
    private long startRowIndex;
    private int firstPage;
    private int lastPage;

    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private boolean hasFirstPage;
    private boolean hasLastPage;

    public Paging(int pageNum, long totalRecode, int pageSize, int pageNavSize) {
        this.pageNum = pageNum;
        this.totalRecode = totalRecode;
        this.pageSize = pageSize;
        this.pageNavSize = pageNavSize;

        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageNavSize < 1) {
            pageSize = 5;
        }
        calculation();
    }

    private void calculation() {
        totalPage = (int) (((totalRecode - 1) / pageSize) + 1);
        if (pageNum > totalPage) {
            pageNum = totalPage;
        }

        firstPage = (((int) Math.ceil((float) pageNum / (float) pageNavSize)) - 1) * pageNavSize + 1;
        lastPage = firstPage + pageNavSize - 1;
        if (lastPage > totalPage) {
            lastPage = totalPage;
        }

        startRowIndex = totalRecode - ((long) (pageNum - 1) * pageSize);

        hasPreviousPage = firstPage != 1;
        hasNextPage = ((long) lastPage * pageSize) < totalRecode;

        hasFirstPage = (pageNum > 1 && pageNavSize < pageNum);
        hasLastPage = (pageNum < totalPage && lastPage + 1 < totalPage);
    }
}
