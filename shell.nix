with (import <nixpkgs> {}).pkgs;

stdenv.mkDerivation {
  name = "passopolis-server-HEAD";
  srcs = ./.;
  buildInputs = [
    openjdk
    ant
    python
  ];
}
