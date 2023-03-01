terraform {
  required_providers {
    azurerm = {
      version = "=3.45.0"
    }
  }
}

provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "experiment1" {
  location = var.location
  name     = "mkheck-tf-rg"

  tags = {
    "Terraform" = "true"
  }
}

resource "azurerm_service_plan" "experiment1" {
  name                = "mkheck-tf-plan"
  resource_group_name = azurerm_resource_group.experiment1.name
  location            = azurerm_resource_group.experiment1.location
  os_type             = "Linux"
  sku_name            = "B1"
}

resource "azurerm_linux_web_app" "experiment1" {
  name                = "mkheck-tf-app"
  resource_group_name = azurerm_resource_group.experiment1.name
  location            = azurerm_service_plan.experiment1.location
  service_plan_id     = azurerm_service_plan.experiment1.id

  https_only = true

  site_config {
#    linux_fx_version = "JAVA:17-java17"
#    MH: Determine which of the following are *actually* necessary, eliminate any others
    application_stack {
      java_server = "JAVA"
      java_server_version = "17"
      java_version = "17"
    }
  }

  app_settings = {
#    Here is where you place Azure Webapp/Spring Boot properties to pass into the app
#    "SPRING_PROFILES_ACTIVE" = "mysql"
    "WEBSITES_PORT" = "8080"
  }
}
