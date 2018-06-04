package com.kotlin.goods.mvp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.kotlin.base.ui.fragment.BaseFragment
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.base.ui.widgets.GlideImageLoader
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.R
import com.kotlin.goods.R.id.*


import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.dagger.component.DaggerGoodsListComponent
import com.kotlin.goods.dagger.module.GoodsListModule
import com.kotlin.goods.event.AddCartEvent
import com.kotlin.goods.event.GoodsDetailImageEvent
import com.kotlin.goods.event.UpdateCartSizeEvent
import com.kotlin.goods.event.UpdateSkuTxtEvent
import com.kotlin.goods.mvp.model.response.GoodsListResponse
import com.kotlin.goods.mvp.presenter.GoodsDetailPresenter
import com.kotlin.goods.mvp.presenter.view.IGoodsDetailView
import com.kotlin.goods.widget.GoodsSkuPopView
import com.orhanobut.logger.Logger
import com.vondear.rxtools.RxSPTool
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer


import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.toast


class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>() ,IGoodsDetailView{


    private var mGoodsList: GoodsListResponse?=null
    private lateinit var mGoodsSkuPopView: GoodsSkuPopView

    override fun injectComponent() {
        DaggerGoodsListComponent.builder().baseActivityComponent(mActiviComponent)
                .build()
                .inject(this)

        mPresenter.mView=this
    }


    override fun getContentViewResId(): Int {
        return R.layout.fragment_goods_detail_tab_one
    }

    override fun initView() {
        EventBusUtils.register(this)
        br_goods_detail_banner.setImageLoader(GlideImageLoader())
        br_goods_detail_banner.setBannerAnimation(Transformer.Accordion)
//        br_goods_detail_banner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        br_goods_detail_banner.setIndicatorGravity(BannerConfig.RIGHT)

        initSkuPop()
        loadData()
        rl_sku_view.setOnClickListener {
            mGoodsSkuPopView.showAtLocation(activity.window.decorView, Gravity.BOTTOM,0,0)

        }
    }

    private fun initSkuPop() {

        mGoodsSkuPopView=GoodsSkuPopView(activity)
    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID,-1))

    }

    /**
     * 添加购物车
     */
    private fun addCart(){
        mGoodsList?.let {
            mPresenter.addCart(

                    it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,

                    mGoodsSkuPopView.getSelectCount(),
                    mGoodsSkuPopView.getSelectSku()

            )
        }

    }



    /**
     * 获取商品详情回调
     */
    override fun onGetGoodsDetailResult(result: GoodsListResponse) {
        this.mGoodsList=result
        br_goods_detail_banner.setImages(result.goodsBanner.split(","))
        br_goods_detail_banner.start()

        tv_goods_desc.text = result.goodsDesc
        tv_goods_price.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        tv_sku_selected.text = result.goodsDefaultSku
        setSkuPopData(result)
        EventBusUtils.post(GoodsDetailImageEvent(result.goodsDetailOne,result.goodsDetailTwo))
    }

    private fun setSkuPopData(result: GoodsListResponse) {

        mGoodsSkuPopView.setGoodsIcon(result.goodsDefaultIcon)
        mGoodsSkuPopView.setGoodsCode(result.goodsCode)
        mGoodsSkuPopView.setGoodsPrice(result.goodsDefaultPrice)


        mGoodsSkuPopView.addSkuView(result.goodsSku)
    }

    override fun onAddCartResult(result: Int) {

        RxSPTool.putInt(activity,GoodsConstant.SP_CART_SIZE,result)
        EventBusUtils.post(UpdateCartSizeEvent())
        Logger.e("onAddCartResult----------》\t\t\t"+result)
    }
    /**
     * 更新sku 文字
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: UpdateSkuTxtEvent){

        tv_sku_selected.text=mGoodsSkuPopView.getSelectSku()+GoodsConstant.SKU_SEPARATOR + mGoodsSkuPopView.getSelectCount()+"件"

    }
    @Subscribe()
    fun onMessageEvent(event: AddCartEvent){

        addCart()

    }

    override fun onDestroy() {
        mGoodsSkuPopView.dismiss()
        EventBusUtils.unregister(this)
        super.onDestroy()
    }
}
