package io.breezil.queryfiersamples.infra;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.breezil.queryfier.serializer.ISerializer;

public class SampleSerializer implements ISerializer {
	
	@Override
	public <T> T deserialize(Class<T> clazz, Object patch) {
		T value = ISerializer.super.deserialize(clazz, patch);
		
		if (value == null && patch != null) {
			try {
				value = new ObjectMapper().readValue(patch.toString(), clazz);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

}
