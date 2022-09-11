package com.nanda.test.dto.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class SearchDTO<T> {

	private boolean success;
	private List<T> listdata;
	private Pagedata pagedata;

	@Data
	@JsonInclude(Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Pagedata {

		private int page;
		private int size;
		private int totalpage;
		private int totaldata;

		public Pagedata() {

		}

		public Pagedata(int page, int size, int totalpage, int totaldata) {
			this.page = page;
			this.size = size;
			this.totalpage = totalpage == 0 ? 1 : totalpage;
			this.totaldata = totaldata;
		}

		public Pagedata(int page, int size, int totalpage, long totaldata) {
			this.page = page;
			this.size = size;
			this.totalpage = totalpage == 0 ? 1 : totalpage;
			this.totaldata = Long.valueOf(totaldata).intValue();
		}

	}

}
