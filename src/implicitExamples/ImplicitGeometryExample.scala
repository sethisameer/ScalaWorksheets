package implicitExamples
/*
  * Implicit class functions to transform to and from esri geometry types
  * usage: <string>.toEsriGeometry => converts from standard geometry to esri geometry format
  * usage: <string>.toStandardGeometry => converts from esri geometry to standard geometry format
 */
object usageExample extends App {
  import ImplicitGeometryExample.GeometryType
  val geometries: List[String] = List("point", "polygon", "multipoint", "polyline", "foo")
  for (geometry <- geometries) yield println(geometry.toEsriGeometry)

  val esriGeometries: List[String] = List("esrigeometrypoint", "esrigeometrypolygon", "esrigeometrymultipoint", "esrigeometrypolyline")
  for (esriGeometry <- esriGeometries) yield println(esriGeometry.toStandardGeometry)
}

object ImplicitGeometryExample {

    trait GeometryUtil[T] {
      def toEsriGeometryType(arg: T): T

      def toStandardGeometryType(arg: T): T
    }

    object GeometryUtil {
      def apply[T](implicit instance: GeometryUtil[T]): GeometryUtil[T] = instance
    }

    implicit class GeometryType[T](genericGeometryType: T) {
      def toEsriGeometry()(implicit geometryInstance: GeometryUtil[T]): T = geometryInstance.toEsriGeometryType(genericGeometryType)

      def toStandardGeometry()(implicit geometryInstance: GeometryUtil[T]): T = geometryInstance.toStandardGeometryType(genericGeometryType)
    }

    implicit val geometryConversions: GeometryUtil[String] = new GeometryUtil[String] {
      override def toEsriGeometryType(arg: String): String = arg.toLowerCase match {
        case "point" => s"EsriGeometryPoint"
        case "polygon" => s"EsriGeometryPolygon"
        case "multipoint" => s"EsriGeometryMultipoint"
        case "polyline" => s"EsriGeometryPolyline"
        case "esrigeometrypoint" | "esrigeometrypolygon" | "esrigeometrymultipoint" | "esrigeometrypolyline" => s"${arg.head.toUpper + arg.tail}"
        case _ => s"Invalid geometry type"
      }

      override def toStandardGeometryType(arg: String): String = arg.toLowerCase match {
        case "esrigeometrypoint" => s"Point"
        case "esrigeometrypolygon" => s"Polygon"
        case "esrigeometrymultipoint" => s"Multipoint"
        case "esrigeometrypolyline" => s"Polyline"
        case "point" | "polygon" | "multipoint" | "polyline" => s"${arg.head.toUpper + arg.tail}"
        case _ => s"Invalid geometry type"
      }
    }
  }


