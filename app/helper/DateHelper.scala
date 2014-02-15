package helper

import java.util.Date
import java.text.SimpleDateFormat
import play.api.i18n.Messages

object DateHelper {
  def format(date: Date): String = {
    new SimpleDateFormat(Messages("day.input.pattern")).format(date)
  }
}