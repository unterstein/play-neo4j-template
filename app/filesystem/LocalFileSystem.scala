package filesystem

import java.io.File
import global.Global

class LocalFileSystem extends FileSystem {

  override def load(token: String): File = {
    new File(Global.getFileSystemPath + token)
  }

  override def store(token: String, file: File): Boolean = {
    file.renameTo(new File(Global.getFileSystemPath + token))
  }
}
