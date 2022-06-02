# First, run this Dockerfile with:
# docker build . -t front-dev -f Dockerfile-build .
# docker run --rm -it -v $(pwd):/usr/src/app front-dev bash
# RUN ng new app --skip-install --directory=./
# RUN yarn install

FROM node:16

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

RUN apt update && apt install git
RUN yarn global add @angular/cli

RUN git config --global user.name reneepc
RUN git config --global user.email rene.cardozo@opus-software.com.br

EXPOSE 4200

CMD ["yarn", "run", "start"]
