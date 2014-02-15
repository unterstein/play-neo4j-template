package filesystem

import java.io.File
import global.Global

object FileSystem {

  def get(): FileSystem = {
    if ("LocalFileSystem" == Global.getFileSystemImplementation()) {
      new LocalFileSystem
    } else {
      // TODO S3 implementation
      null
    }
  }
}

abstract class FileSystem {

  def load(token: String): File

  def store(token: String, file: File): Boolean
}
