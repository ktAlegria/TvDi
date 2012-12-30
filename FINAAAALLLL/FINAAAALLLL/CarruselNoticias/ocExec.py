import os

os.system('/usr/local/bin/tscbrmuxer b:233220 video.ts b:192000 audio.ts b:3008 mptspat.ts b:3008 mptspmt1.ts b:1500 mptssdt.ts b:1400 mptsnit.ts b:2000 firstait.ts b:1000000 ocdir.ts b:11834864 null.ts > fifo1.ts & tsstamp fifo1.ts 13271000 > fifo2.ts & DtPlay fifo2.ts -t 110 -mt OFDM -mC QAM16 -mG 1/4 -mc 2/3 -mf 578p')
