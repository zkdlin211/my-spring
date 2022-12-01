package com.cliffe.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 11:19 上午
 *
 * Define the Resource interface: Provide a method to get an InputStream
 *
 * 3 implementation perform different stream file operations:
 * classPath, FileSystem, and URL
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
