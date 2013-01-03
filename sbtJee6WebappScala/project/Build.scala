import sbt._
import Keys._
import com.github.siasia._
import PluginKeys._

object DummySbtScalaWicket extends Build {

  val explodeTask = TaskKey[Unit]("explode")
  val explodeDir = "tmp/"

  val setngs = Seq(
    explodeTask <<= (auxCompile in Compile) map { _ =>
      explodeBody()
    }
  )
  
  def explodeBody(){    
    println("Test 5")
  }

  val root = Project("root", file(".")) settings(setngs:_*)

  override val projects = Seq(root)  
  
  def auxCompileTask22 = (compile, crossTarget, classDirectory, excludeFilter) map {
		(_, ct, cd, filter) =>
		println("Test -1")
		/*val auxCd = ct / "aux-classes"
		val classes = for {
			file <- cd.descendentsExcept("*", filter).get
			val target = Path.rebase(cd, auxCd)(file).get
		} yield (file, target)
		val copied = IO.copy(classes)
		val toRemove = scala.collection.mutable.HashSet((auxCd ** "*").get.toSeq : _*) -- copied
		val (dirs, files) = toRemove.toList.partition(_.isDirectory)
		IO.delete(files)
		IO.deleteIfEmpty(dirs.toSet)*/
	}


}
