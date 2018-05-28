#!/bin/bash

hiptest-publisher -c hiptest/hiptest-publisher.config --without=actionwords --overriden-templates=hiptest/templates --token=$1 --test-run-id=$2
