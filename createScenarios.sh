#!/bin/bash

hiptest-publisher -c hiptest/hiptest-publisher.config --without=actionwords --filter-on-tag=manual--overriden-templates=hiptest/templates --token=$1
