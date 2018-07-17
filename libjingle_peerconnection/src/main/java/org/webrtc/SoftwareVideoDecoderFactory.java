/*
 *  Copyright 2017 The WebRTC project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a BSD-style license
 *  that can be found in the LICENSE file in the root of the source
 *  tree. An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */

package org.webrtc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;

public class SoftwareVideoDecoderFactory implements VideoDecoderFactory {
  @Nullable
  @Override
  public VideoDecoder createDecoder(VideoCodecInfo codecType) {
    if (codecType.getName().equalsIgnoreCase("VP8")) {
      return new VP8Decoder();
    }
    if (codecType.getName().equalsIgnoreCase("VP9") && VP9Decoder.nativeIsSupported()) {
      return new VP9Decoder();
    }

    return null;
  }

  @Override
  public VideoCodecInfo[] getSupportedCodecs() {
    return supportedCodecs();
  }

  static VideoCodecInfo[] supportedCodecs() {
    List<VideoCodecInfo> codecs = new ArrayList<VideoCodecInfo>();

    codecs.add(new VideoCodecInfo("VP8", new HashMap<>()));
    if (VP9Decoder.nativeIsSupported()) {
      codecs.add(new VideoCodecInfo("VP9", new HashMap<>()));
    }

    return codecs.toArray(new VideoCodecInfo[codecs.size()]);
  }
}
