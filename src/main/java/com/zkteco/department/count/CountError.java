package com.zkteco.department.count;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CountError {

	public int errorCount;
	public Object error1;
}
