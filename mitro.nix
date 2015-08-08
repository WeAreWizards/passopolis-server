{ stdenv, antBuild, fetchurl, unzip, perl, git, python }:

let
  version = "HEAD";

in antBuild rec {
  name = "mitro-core-${version}";

  patches = [ ./dont-fail-on-warn.patch ];
  configurePhase = ''
    cd mitro-core
  '';
  buildInputs = [ git python unzip ];
  src = fetchurl {
    url = "https://github.com/mitro-co/mitro/archive/ae43f8346de6c3e9818988a08cea448393e4af52.zip";
    sha256 = "009abrypmp7l3wxp8pgfb3jxn6asr9q6ww4vi8759blzqwcchnji";
  };
  antTargets = [ "crypto" "jar" ];

  antProperties = [
    { name = "version"; value = version; }
  ];
}
