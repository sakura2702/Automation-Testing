/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.service;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * DriverLoaderHelper -
 *
 * @author phuong.nguyenthi
 */
public class DriverLoaderHelper {
  private final ProtectionDomain domain;
  private final CodeSource source;
  private final URL url;

  public DriverLoaderHelper(){
    domain = DriverLoaderHelper.class.getProtectionDomain();
    source = domain.getCodeSource();
    url = source.getLocation();
  }

  public URI getFile(String fileName) throws ZipException, IOException, URISyntaxException {
    return getFile(getJarUri(), fileName);
  }


  public URI getFile(final URI where, final String fileName) throws ZipException, IOException {
    File location;
    URI fileURI;
    location = new File(where);
    // not in a JAR, just return the path on disk
    if (location.isDirectory()) {
      fileURI = URI.create(where.toString() + fileName);
    } else {
      final ZipFile zipFile;

      zipFile = new ZipFile(location);

      try {
        fileURI = extract(zipFile, fileName);
      } finally {
        zipFile.close();
      }
    }

    return (fileURI);
  }

  private URI extract(final ZipFile zipFile,
                             final String fileName)
    throws IOException {
    final File tempFile;
    final ZipEntry entry;
    final InputStream zipStream;
    OutputStream fileStream;

    tempFile = File.createTempFile(fileName, Long.toString(System.currentTimeMillis()));
    tempFile.deleteOnExit();
    entry = zipFile.getEntry(fileName);

    if (entry == null) {
      throw new FileNotFoundException("Cannot find file: " + fileName + " in archive: " + zipFile.getName());
    }

    zipStream = zipFile.getInputStream(entry);
    fileStream = null;

    try {
      final byte[] buf;
      int i;

      fileStream = new FileOutputStream(tempFile);
      buf = new byte[1024];
      i = 0;

      while ((i = zipStream.read(buf)) != -1) {
        fileStream.write(buf, 0, i);
      }
    } finally {
      close(zipStream);
      close(fileStream);
    }

    return (tempFile.toURI());
  }

  private void close(final Closeable stream) {
    if (stream != null) {
      try {
        stream.close();
      } catch (final IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  public URI getJarUri() throws URISyntaxException {
    return url.toURI();
  }
}
