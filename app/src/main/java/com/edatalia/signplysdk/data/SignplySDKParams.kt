package com.edatalia.signplysdk.data

import android.os.Parcelable
import com.edatalia.signplysdk.data.SIGNplySDKConstants.DEFAULT_CUSTOM_TEXT_FONT_SIZE
import com.edatalia.signplysdk.data.SIGNplySDKConstants.DEFAULT_FIXED_WIDGET_HEIGHT
import com.edatalia.signplysdk.data.SIGNplySDKConstants.DEFAULT_FIXED_WIDGET_WIDTH
import com.edatalia.signplysdk.data.SIGNplySDKConstants.DEFAULT_MANUAL_WIDGET_RATIO
import com.edatalia.signplysdk.data.SIGNplySDKConstants.DEFAULT_NAME_SIGNED_DOCUMENT
import com.edatalia.signplysdk.data.SIGNplySDKConstants.DEFAULT_RENDER_INDEX_PAGE
import com.edatalia.signplysdk.data.SIGNplySDKConstants.DEFAULT_SIGNATURE_COLOR_HEX
import com.edatalia.signplysdk.data.SIGNplySDKConstants.DEFAULT_SIGNATURE_THICKNESS
import kotlinx.parcelize.Parcelize
import java.lang.RuntimeException
import java.security.InvalidParameterException
import kotlin.collections.ArrayList

@Parcelize
data class SignplySDKParams(
    var licenseB64: String,
    var fileProperties: SignplySDKFileProperties,
    var commonProperties: SignplySDKCommonProperties,
    var recipients: List<SignplySDKRecipient>
) : Parcelable

@Parcelize
data class SignplySDKFileProperties(
    var uriPath: String,
    var signedName: String = DEFAULT_NAME_SIGNED_DOCUMENT,
    var isShareable: Boolean = false,
    var password: String? = null,
    var author: String? = null,
    var reason: String? = null,
    var contact: String? = null,
    var location: String? = null
) : Parcelable

@Parcelize
data class SignplySDKCommonProperties(
    var title: String? = null,
    var requestLocation: Boolean = false,
    var simulateTestingSignature: Boolean = false,
    var saveOnPrivateStorage: Boolean = false,
    var renderDefaultIndexPage: Int = DEFAULT_RENDER_INDEX_PAGE,
    var showRecipientList: Boolean = true
) : Parcelable

@Parcelize
data class SignplySDKRecipient(
    var name: String,
    var role: String? = null,
    var isEnabled: Boolean = true,
    var hasSigned: Boolean = false,
    var widget: SignplySDKWidget = SignplySDKWidget(),
    var tsp: SignplySDKTSP = SignplySDKTSP(),
    var extra: SignplySDKExtra = SignplySDKExtra(),
    var certificate: SignplySDKCertificate = SignplySDKCertificate()
) : Parcelable

@Parcelize
data class SignplySDKWidget(
    var widgetType: SignplySDKWidgetType = SignplySDKWidgetType.MANUAL,
    var widgetFloatText: String? = null,
    var widgetFieldFieldname: String? = null,
    var widgetManualRatio: Float = DEFAULT_MANUAL_WIDGET_RATIO,
    var widgetFixedPage: Int? = null,
    var widgetFixedX: Int? = null,
    var widgetFixedY: Int? = null,
    var widgetFloatGapY: Int? = null,
    var widgetFloatGapX: Int? = null,
    var widgetCustomText: List<SignplySDKWidgetCustomText> ? = ArrayList(),
    var requestWidgetCustomText: Boolean? = false,
    var widgetWidth: Int = DEFAULT_FIXED_WIDGET_WIDTH,
    var widgetHeight: Int = DEFAULT_FIXED_WIDGET_HEIGHT,
) : Parcelable

enum class SignplySDKWidgetType {
    MANUAL, FIELD, FIXED, FLOAT
}

@Parcelize
data class SignplySDKTSP(
    var tspActivate: Boolean = false,
    var tspURL: String? = null,
    var tspUser: String? = null,
    var tspPassword: String? = null
) : Parcelable

@Parcelize
data class SignplySDKWidgetCustomText(
    var text: String = "",
    var fontSize: Int = DEFAULT_CUSTOM_TEXT_FONT_SIZE,
) : Parcelable

@Parcelize
data class SignplySDKExtra(
    var autoOpen: Boolean = false,
    var viewLastPage: Boolean = false,
    var signatureColorHex: String = DEFAULT_SIGNATURE_COLOR_HEX,
    var signatureThickness: Int = DEFAULT_SIGNATURE_THICKNESS,
    var showReject: Boolean = false,
    var captureSignatureTitle: String? = null
) : Parcelable

@Parcelize
data class SignplySDKCertificate(
    var signCertP12B64: String? = null,
    var signCertPassword: String? = null,
    var requestUserCertificate: Boolean = false,
    var defaultAliasUserCertificate: String? = null,
    var encKeyB64: String? = null,
    var ltv: Boolean = false
) : Parcelable

object SIGNplySDKConstants {
    const val DEFAULT_FIXED_WIDGET_WIDTH = 150
    const val DEFAULT_FIXED_WIDGET_HEIGHT = 75
    const val DEFAULT_MANUAL_WIDGET_RATIO = 2.5f
    const val DEFAULT_SIGNATURE_COLOR_HEX = "#000000"
    const val DEFAULT_SIGNATURE_THICKNESS = 10
    const val DEFAULT_CUSTOM_TEXT_FONT_SIZE = 8
    const val DEFAULT_RENDER_INDEX_PAGE = 0
    const val DEFAULT_NAME_SIGNED_DOCUMENT = "Document.pdf"
}

class SignplySDKInvalidLicenseException(message: String?) : SecurityException(message)
class SignplySDKInvalidParamsException(message: String?) : InvalidParameterException(message)
class SignplySDKRenderPDFException(message: String?) : RuntimeException(message)
class SignplySDKSignPDFException(message: String?) : RuntimeException(message)