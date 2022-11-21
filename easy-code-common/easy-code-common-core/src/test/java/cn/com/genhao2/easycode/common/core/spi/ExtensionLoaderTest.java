package cn.com.genhao2.easycode.common.core.spi;

import cn.hutool.core.lang.Assert;
import org.junit.Test;

import java.util.List;

public class ExtensionLoaderTest {


	@Test
	public void getDefaultExtension() {

		Filter defaultExtensionFilter = ExtensionLoader.getExtensionLoader(Filter.class).getDefaultExtension();
		Assert.isNull(defaultExtensionFilter);
		FilterWithSpi defaultExtension = ExtensionLoader.getExtensionLoader(FilterWithSpi.class).getDefaultExtension();
		Assert.notNull(defaultExtension);
		Filter xss = ExtensionLoader.getExtensionLoader(Filter.class).getExtension("xss");
	}


	@Test
	public void getExtensionNames() {
		List<String> extensionNames = ExtensionLoader.getExtensionLoader(Filter.class).getExtensionNames();
		Assert.isTrue(extensionNames.contains("xss"));
	}


	@Test
	public void hasExtension() {
		Assert.isTrue(ExtensionLoader.getExtensionLoader(Filter.class).hasExtension("xss"));
	}

}