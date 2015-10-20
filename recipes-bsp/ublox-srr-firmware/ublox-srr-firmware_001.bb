# Copyright (C) 2014 u-blox AG
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "u-blox ODIN-W16 firmware"
HOMEPAGE = "http://www.u-blox.com/en/bluetooth-wifi-multiradio-modules/multiradio-wi-fi-bluetooth-modules/odin-w160-wi-fi-bluetooth-module.html"
LICENSE = "TI-Limited"
SECTION = "network/misc"
PR = "r1+gitr${SRCPV}"

LIC_FILES_CHKSUM = "file://odin-w16/LICENCE;md5=1c9961176d6529283e0d0c983be41b45"

SRCREV = "19694957701e9e2efb51b1686047db61ac23632c"
SRC_URI = "git://${UBLOX_GIT}/ublox-srr-firmware.git;protocol=${UBLOX_GIT_PROTOCOL}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Firmware is only allowed to be used on ti products.
# This firmware is targeted for ti33x
COMPATIBLE_MACHINE = "ti33x"

S = "${WORKDIR}/git"

do_compile() {
    :
}

do_install() {
    # Copy firmware for BT and WLAN
    install -d ${D}/${base_libdir}/firmware
    install -d ${D}/${base_libdir}/firmware/ti-connectivity
    install -m 644 ${S}/odin-w16/TIInit_7.6.15_odin-w16.bts ${D}/${base_libdir}/firmware
    install -m 644 ${S}/odin-w16/wl1273-odin-w16.bin ${D}/${base_libdir}/firmware/ti-connectivity
    install -m 644 ${S}/odin-w16/wl127x-fw-5-plt.bin ${D}/${base_libdir}/firmware/ti-connectivity
    install -m 644 ${S}/odin-w16/LICENCE \
        ${D}/${base_libdir}/firmware/LICENCE.TIInit

    # Create symlinks to the drivers expected firmware names
    ln -sf ti-connectivity/wl1273-odin-w16.bin ${D}/${base_libdir}/firmware/wl127x-fw-5-sr.bin
    ln -sf wl1273-odin-w16.bin ${D}/${base_libdir}/firmware/ti-connectivity/wl127x-fw-5-sr.bin
    ln -sf ../TIInit_7.6.15_odin-w16.bts ${D}/${base_libdir}/firmware/ti-connectivity/TIInit_7.6.15.bts
    ln -sf TIInit_7.6.15_odin-w16.bts ${D}/${base_libdir}/firmware/TIInit_7.6.15.bts
}

FILES_${PN} += "${base_libdir}/firmware"
