package com.zkteco.department.count;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CountSuccess {

	public int successCount;
	public Object count;
}
