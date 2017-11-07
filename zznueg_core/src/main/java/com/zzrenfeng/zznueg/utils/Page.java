package com.zzrenfeng.zznueg.utils;

import java.util.List;

/**
 * @功能描述：前台分页基础类；由于框架是final类，无法继承，所以结合需求新创建
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年8月8日 上午10:20:02
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
@SuppressWarnings("rawtypes")
public final class Page {
	/**
     * 分页查询开始记录位置.
     */
    private int begin;
    /**
     * 分页查看下结束位置.
     */
    private int end;
    /**
     * 每页显示记录数.
     */
    private int length = 20;
    /**
     * 查询结果总记录数.
     */
    private int totalRecords;
    /**
     * 当前页码.
     */
    private int pageNo;
    /**
     * 总共页数.
     */
    private int pageCount;
    
    /**
     *分页结果
     */
    private List pageResult;
    
    public Page() {
    }

    /**
     * 构造函数.
     * <p>
     * param begin
     * param length
     */
    public Page(int begin, int length) {
        this.begin = begin;
        this.length = length;
        this.end = this.begin + this.length;
        this.pageNo = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;
    }

    /**
     * param begin
     * param length
     * param totalRecords
     */
    public Page(int begin, int length, int totalRecords) {
        this(begin, length);
        this.totalRecords = totalRecords;
    }

    /**
     * 设置页数，自动计算数据范围.
     * <p>
     * param pageNo
     */
    public Page(int pageNo) {
        this.pageNo = pageNo;
        pageNo = pageNo > 0 ? pageNo : 1;
        this.begin = this.length * (pageNo - 1);
        this.end = this.length * pageNo;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }


    public void setEnd(int end) {
        this.end = end;
    }

    public void setBegin(int begin) {
        this.begin = begin;
        if (this.length != 0) {
            this.pageNo = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        if (this.begin != 0) {
            this.pageNo = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;
        }
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
        this.pageCount = (int) Math.floor((this.totalRecords * 1.0d) / this.length);
        if (this.totalRecords % this.length != 0) {
            this.pageCount++;
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        pageNo = pageNo > 0 ? pageNo : 1;
        this.begin = this.length * (pageNo - 1);
        this.end = this.length * pageNo;
    }

    public int getPageCount() {
        if (pageCount == 0) {
            return 1;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

	public List getPageResult() {
		return pageResult;
	}

	public void setPageResult(List pageResult) {
		this.pageResult = pageResult;
	}
    
}
