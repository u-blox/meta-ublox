# u-blox ELIN-W160 System on Module machine configuration
#

SOC_FAMILY = "ti33x"
require conf/machine/include/soc-family.inc

DEFAULTTUNE ?= "cortexa8thf-neon"
require conf/machine/include/tune-cortexa8.inc

PREFERRED_PROVIDER_virtual/kernel ?= "linux-ublox"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-ublox"

KERNEL_IMAGETYPE = "zImage"

UBOOT_ARCH = "arm"
UBOOT_MACHINE ?= "elin-w160-evk"
UBOOT_ENTRYPOINT ?= "0x80008000"
UBOOT_LOADADDRESS ?= "0x80008000"

MKUBIFS_ARGS = "-F -m 2048 -e 124KiB -c 3488"
UBINIZE_ARGS = "-m 2048 -p 128KiB"
UBI_VOLNAME = "rootfs"

USE_VT = "0"

MACHINE_FEATURES = "bluetooth wifi"

MACHINE_EXTRA_RRECOMMENDS = "crda ublox-srr-firmware"

EXTRA_IMAGEDEPENDS += "virtual/bootloader"
