package utils




import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.rorydemo.R


/**
 * Author by roryLin, Email xx@xx.com, Date on 2020/8/12.
 */

object ToastUtils{

    fun showCenterToast(context:Context,txt:String){
        val toastview: View = LayoutInflater.from(context.applicationContext).inflate(R.layout.toast_cust, null)
        toastview.findViewById<TextView>(R.id.tv_message).text = txt
        val toast = Toast(context.applicationContext)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = toastview
        toast.show()
    }
}