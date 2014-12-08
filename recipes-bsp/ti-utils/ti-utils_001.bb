# Copyright (C) u-blox AG 2014

DESCRIPTION = "The calibrator and other useful utilities for TI wireless solution based on wl12xx driver"
LICENSE = "ti-utils"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=4725015cb0be7be389cf06deeae3683d \
    "

DEPENDS = "libnl"

PR ="r0+gitr${SRCPV}"
PV ="0.1"

SRC_URI = "git://github.com/ti-openlink/ti-utils.git \
			file://libnl3.patch"

SRCREV = "1971f622534da197994b3af6b8a73d38c75f768e"

S = "${WORKDIR}/git"

export CROSS_COMPILE = "${TARGET_PREFIX}"

TARGET_CC_ARCH += "${LDFLAGS} -DCONFIG_LIBNL20 -I${STAGING_INCDIR}/libnl3/ -L${STAGING_LIBDIR} -lnl-3 -lnl-genl-3 -lm"

do_compile() {
	oe_runmake
}

do_install() {
	install -d ${D}${sbindir}
	install -m 0755 calibrator ${D}${sbindir}/
}

FILES_${PN} += " \
	${datadir}/ti/wifi-utils/ini_files/127x \
"
