package com.example.sharetest

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sharetest.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }
    var uri: Uri? = null
    var currPkg: String = ""

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            uri = data?.getData();
            ShareUtils.intentMessageWithPicture(activity, "测试图片分享",currPkg, uri)
        }
    }

    var shareType = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



        val clickListener : View.OnClickListener = View.OnClickListener { v ->
            when(v!!.id){
                R.id.face_book_btn -> toShare(ShareUtils.FACEBOOK_PKG, shareType)
                R.id.twitter_btn -> toShare(ShareUtils.TWITTER_PKG, shareType)
                R.id.pinterest_btn -> toShare(ShareUtils.PINTEREST_PKG, shareType)
                R.id.instagram_btn -> toShare( ShareUtils.INSTAGRAM_PKG, shareType)
                R.id.line_btn -> toShare(ShareUtils.LINE_PKG, shareType)
                R.id.telegram_btn -> toShare(ShareUtils.TELEGRAM_PKG, shareType)
                R.id.whatsapp_btn -> toShare(ShareUtils.WHATSAPP_PKG, shareType)
                R.id.switch_btn -> {
                    if(shareType == 0) {
                        shareType = 1
                        binding.infoTv.text = "图片分享"
                    }else if(shareType == 1){
                        shareType = 0
                        binding.infoTv.text = "文本分享"
                    }
                }
            }
        }
        binding.switchBtn.setOnClickListener(clickListener)
        binding.faceBookBtn.setOnClickListener(clickListener)
        binding.twitterBtn.setOnClickListener(clickListener)
        binding.pinterestBtn.setOnClickListener(clickListener)
        binding.instagramBtn.setOnClickListener(clickListener)
        binding.lineBtn.setOnClickListener(clickListener)
        binding.telegramBtn.setOnClickListener(clickListener)
        binding.whatsappBtn.setOnClickListener(clickListener)
    }

    fun toShare(pkg: String, shareType: Int){
        currPkg = pkg
        if(shareType == 0){
            ShareUtils.intentMessageWithText(activity, "测试文本分享",pkg)
        }else if(shareType == 1){
            var intent = Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            startActivityForResult(intent, 123);
//            ShareUtils.intentMessageWithPicture(activity, "测试图片分享",pkg, uri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}